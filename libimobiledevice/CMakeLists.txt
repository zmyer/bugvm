cmake_minimum_required(VERSION 2.8)
project(bugvm-libimobiledevice)
include(ExternalProject)

find_package(JNI REQUIRED)

if(OS STREQUAL "linux")
  set(LINUX YES)
elseif(OS STREQUAL "macosx")
  set(MACOSX YES)
endif()

if (ARCH STREQUAL "x86")
  set(X86 YES)
elseif (ARCH STREQUAL "x86_64")
  set(X86_64 YES)
endif()

set(C_CXX_FLAGS "${C_CXX_FLAGS} -fdata-sections -ffunction-sections")
if(MACOSX AND X86_64)
  set(C_CXX_FLAGS "${C_CXX_FLAGS} -arch x86_64 -mmacosx-version-min=10.7")
  set(LD_FLAGS "${LD_FLAGS} -arch x86_64 -mmacosx-version-min=10.7")
elseif(LINUX AND X86)
  set(C_CXX_FLAGS "${C_CXX_FLAGS} -m32 -fPIC")
  set(LD_FLAGS "${LD_FLAGS} -m32")
elseif(LINUX AND X86_64)
  set(C_CXX_FLAGS "${C_CXX_FLAGS} -m64 -fPIC")
  set(LD_FLAGS "${LD_FLAGS} -m64")
endif()

if(MACOSX)
  exec_program(xcode-select ARGS --print-path OUTPUT_VARIABLE XCODE_PATH)
  set(SYSROOT "${XCODE_PATH}/Platforms/MacOSX.platform/Developer/SDKs/MacOSX10.11.sdk")
  if(NOT IS_DIRECTORY ${SYSROOT})
    message(FATAL_ERROR "No supported MacOSX SDK found")
  endif()
  set(C_CXX_FLAGS "${C_CXX_FLAGS} -isysroot ${SYSROOT}")
endif()

set(EXTPREFIX "${CMAKE_BINARY_DIR}/ext")
set(EXTCFLAGS "${C_CXX_FLAGS} -I${EXTPREFIX}/include -I${EXTPREFIX}/include/libusb-1.0 -I${EXTPREFIX}/include/libxml2")
ExternalProject_Add(extlibxml
  URL https://github.com/bugvm/bugvm/releases/download/bugvm-ext/libxml2-2.9.1.tar.gz
  CONFIGURE_COMMAND bash -c "CC=${CMAKE_C_COMPILER} CFLAGS='${EXTCFLAGS}' ./configure --enable-shared=no --prefix=${EXTPREFIX} --with-minimum=on --without-python --without-ftp --without-http --without-html --without-docbook --without-xpath --without-xinclude --without-xptr --without-c14n --without-catalog --without-valid --without-schemas --without-schematron --without-modules --without-lzma --without-zlib"
  BUILD_IN_SOURCE 1
)
ExternalProject_Add(extlibusbx
  URL https://github.com/bugvm/bugvm/releases/download/bugvm-ext/libusb-1.0.20.tar.bz2
  CONFIGURE_COMMAND bash -c "CC=${CMAKE_C_COMPILER} CFLAGS='${EXTCFLAGS}' ./configure --enable-shared=no --enable-udev=no --prefix=${EXTPREFIX}"
  BUILD_IN_SOURCE 1
)
ExternalProject_Add(extlibplist
  URL https://github.com/bugvm/bugvm/releases/download/bugvm-ext/libplist-1.12.tar.bz2
  CONFIGURE_COMMAND bash -c "CC=${CMAKE_C_COMPILER} CFLAGS='${EXTCFLAGS}' PKG_CONFIG_PATH='${EXTPREFIX}/lib/pkgconfig' ./configure --enable-shared=no --prefix=${EXTPREFIX} --without-cython"
  BUILD_IN_SOURCE 1
  DEPENDS extlibxml
)
ExternalProject_Add(extlibusbmuxd
  URL https://github.com/bugvm/bugvm/releases/download/bugvm-ext/libusbmuxd-1.0.10.tar.bz2
  CONFIGURE_COMMAND bash -c "CC=${CMAKE_C_COMPILER} CFLAGS='${EXTCFLAGS}' PKG_CONFIG_PATH='${EXTPREFIX}/lib/pkgconfig' ./configure --enable-shared=no --prefix=${EXTPREFIX}"
  BUILD_IN_SOURCE 1
  DEPENDS extlibusbx extlibplist
)
set(LIBIMOBILEDEVICE_CFLAGS "${EXTCFLAGS}")
if(MACOSX)
  set(LIBIMOBILEDEVICE_CFLAGS "${EXTCFLAGS} -std=gnu89")
endif()

ExternalProject_Add(extlibimobiledevice
  URL https://github.com/bugvm/bugvm/releases/download/bugvm-ext/libimobiledevice-1.1.7.tar.bz2
  CONFIGURE_COMMAND bash -c "CC=${CMAKE_C_COMPILER} CFLAGS='${LIBIMOBILEDEVICE_CFLAGS} -I/usr/local/include -I/usr/local/opt/openssl/include' LDFLAGS='-L${EXTPREFIX}/lib -L/usr/local/lib -L/usr/local/opt/openssl/lib -lxml2' PKG_CONFIG_PATH='${EXTPREFIX}/lib/pkgconfig' ./configure --prefix ${EXTPREFIX} --enable-shared=no --without-cython"
  BUILD_IN_SOURCE 1
  DEPENDS extlibxml extlibplist extlibusbmuxd
)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} ${C_CXX_FLAGS}")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} ${C_CXX_FLAGS} -fno-rtti")
set(CMAKE_INSTALL_DO_STRIP YES)

include_directories(${JAVA_INCLUDE_PATH} ${JAVA_INCLUDE_PATH2} ${EXTPREFIX}/include)

set(LIBS -limobiledevice -lusbmuxd -lplist -lssl -lxml2 -lcrypto)
set(LDFLAGS -L${EXTPREFIX}/lib -L/usr/local/lib -L/usr/local/lib -L/usr/local/opt/openssl/lib)

if(MACOSX)
  set(LIBS ${LIBS} -liconv)
endif()

set(SRC
  src/main/native/libimobiledevice_wrap.c
)

add_library(bugvm-libimobiledevice SHARED ${SRC})
add_dependencies(bugvm-libimobiledevice extlibimobiledevice)
target_link_libraries(bugvm-libimobiledevice ${LIBS} ${LDFLAGS})
if(LINUX)
  set_target_properties(bugvm-libimobiledevice PROPERTIES LINK_FLAGS "-Wl,--gc-sections -Wl,--version-script=${CMAKE_SOURCE_DIR}/src/main/native/exports_linux.sym")
elseif(MACOSX)
  set_target_properties(bugvm-libimobiledevice PROPERTIES LINK_FLAGS "-Wl,-dead_strip -Wl,-exported_symbols_list -Wl,${CMAKE_SOURCE_DIR}/src/main/native/exports_macosx.sym")
endif()
set(INSTALL_DIR ${CMAKE_SOURCE_DIR}/src/main/resources/com/bugvm/libimobiledevice/binding/${OS}/${ARCH})
install(TARGETS bugvm-libimobiledevice DESTINATION ${INSTALL_DIR})
