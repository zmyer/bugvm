function_offset       = 0  # void*
stackArgsSize_offset  = 4  # jint
stackArgsIndex_offset = 8  # jint
stackArgs_offset      = 12 # void**
returnValue_offset    = 16 # FpIntValue
returnType_offset     = 24 # jint
CallInfo_size         = 28

    .section    __TEXT,__text,regular,pure_instructions
    
    .globl    __call0
    .align    4, 0x90
__call0:
Lcall0Begin:
    pushl %ebp
Lcall0CFI0:
    mov   %esp, %ebp
Lcall0CFI1:
    mov   8(%ebp), %eax         # %eax = First arg (CallInfo*)

    mov   stackArgsSize_offset(%eax), %ecx # %ecx = stackArgsSize
    
    shl   $2, %ecx              # %ecx equals number of bytes needed by the stack args
    and   $15, %ecx             # %ecx mod 16
    neg   %ecx
    add   $16, %ecx             # %ecx = 16 - %ecx
    sub   %ecx, %esp            # Adjust %esp
    sub   $8, %esp              # 4 bytes for %ebp pushed in the prologue and 4 bytes for return address
    
    mov   stackArgsSize_offset(%eax), %ecx # %ecx = stackArgsSize
    
LsetStackArgsNext:
    test  %ecx, %ecx
    je    LsetStackArgsDone
    dec   %ecx
    mov   stackArgs_offset(%eax), %edx     # %edx = stackArgs
    lea   (%edx, %ecx, 4), %edx  # %edx = stackArgs + %ecx * 4
    push  (%edx)
    jmp   LsetStackArgsNext
LsetStackArgsDone:

Lcall0TryCatchStart:
    call  *function_offset(%eax)
Lcall0TryCatchEnd:
Lcall0TryCatchLandingPad:

    leave
    ret
Lcall0End:

    .section    __TEXT,__gcc_except_tab
    .align    2
GCC_except_table1:
Lexception1:
    .byte    255                     # @LPStart Encoding = omit
    .byte    155                     # @TType Encoding = indirect pcrel sdata4
    .byte    158                     # @TType base offset
    .space   2,128
    .space   1
    .byte    3                       # Call site Encoding = udata4
    .uleb128 26                      # Call site table length
    .long    Lcall0TryCatchStart - Lcall0Begin        # Region start
    .long    Lcall0TryCatchEnd - Lcall0TryCatchStart  # Region length
    .long    Lcall0TryCatchLandingPad - Lcall0Begin   # Landing pad
    .uleb128 1                                                # Action
    .long    Lcall0TryCatchEnd - Lcall0Begin          # Region start
    .long    Lcall0End - Lcall0TryCatchEnd            # Region length
    .long    0                       # Landing pad
    .uleb128 0                       # Action
                                       # -- Action Record Table --
                                       # Action Record
    .sleb128 -1                      #   TypeInfo index
    .sleb128 0                       #   Next action
                                       # -- Filter IDs --
    .uleb128 0
    .align   2

    .section    __TEXT,__eh_frame,coalesced,no_toc+strip_static_syms+live_support
EH_frame0:
Lsection_eh_frame0:
Leh_frame_common0:
    .long    Leh_frame_common_end0 - Leh_frame_common_begin0 # Length of Common Information Entry
Leh_frame_common_begin0:
    .long    0                       # CIE Identifier Tag
    .byte    1                       # DW_CIE_VERSION
    .asciz   "zPLR"                  # CIE Augmentation
    .uleb128 1                       # CIE Code Alignment Factor
    .sleb128 -4                      # CIE Data Alignment Factor
    .byte    8                       # CIE Return Address Column
    .uleb128 7                       # Augmentation Size
    .byte   155                      # Personality Encoding = indirect pcrel sdata4
    .long   L__nvmPersonality$non_lazy_ptr - . # Personality
    .byte    16                      # LSDA Encoding = pcrel
    .byte   16                       # FDE Encoding = pcrel
    # CFA is in %esp+4 when entering a function
    .byte    12                      # DW_CFA_def_cfa
    .byte    5                       # Register
    .byte    4                       # Offset
    # Return address is at CFA-4
    .byte    136                     # DW_CFA_offset + Reg (8)
    .uleb128 1                       # Offset
    .align   2
Leh_frame_common_end0:

    .globl    __call0.eh
__call0.eh:
    .long    Lcall0eh_frame_end0 - Lcall0eh_frame_begin0 ## Length of Frame Information Entry
Lcall0eh_frame_begin0:
    .long    Lcall0eh_frame_begin0 - Leh_frame_common0 ## FDE CIE offset
    .long    Lcall0Begin - .         ## FDE initial location
    .long    Lcall0End - Lcall0Begin ## FDE address range
    .uleb128 4                       ## Augmentation size
    .long    Lexception1 - .         ## Language Specific Data Area
    # Advance to Lcall0CFI0
    .byte    4                       ## DW_CFA_advance_loc4
    .long    Lcall0CFI0 - Lcall0Begin
    # CFA is now in %esp+8
    .byte    14                      ## DW_CFA_def_cfa_offset
    .uleb128 8                       ## Offset
    # Value of %ebp is now at CFA-8
    .byte    132                     ## DW_CFA_offset + Reg (4)
    .uleb128 2                       ## Offset
    # Advance to Lcall0CFI1
    .byte    4                       ## DW_CFA_advance_loc4
    .long    Lcall0CFI1 - Lcall0CFI0
    # CFA is now in %ebp+8
    .byte    13                      ## DW_CFA_def_cfa_register
    .uleb128 4                       ## Register
    .align   2
Lcall0eh_frame_end0:

        .section        __IMPORT,__pointers,non_lazy_symbol_pointers
L__nvmPersonality$non_lazy_ptr:
        .indirect_symbol        __nvmPersonality
        .long   0
