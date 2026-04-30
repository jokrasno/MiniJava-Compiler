# PROMPT for Agent: Build a Unified MiniJava-to-MIPS Compiler

## Overall Task

You are building a complete, self-contained MiniJava compiler that compiles `.java` (MiniJava subset) source files into MIPS assembly. The project combines work from 5 assignments into a single clean repository in the `COMPILER/` folder.

The student wrote one major file per assignment. All other `.java` files are instructor-provided infrastructure (AST nodes, visitors, error handling, main entry point, etc.).

### Student-authored files (copy from these locations):

| Assignment | File(s) | Source Path (relative to `CS-358/`) |
|---|---|---|
| 1 | `TokenGrammar.java` | `asst1/asst1/asst1Starter/` |
| 2 | `MJGrammar.java` | `asst2/asst2/asst2Starter/` |
| 3 | `Sem1Visitor.java`, `Sem2Visitor.java`, `Sem3Visitor.java` | `asst3/asst3Starter/asst3Starter/` |
| 4 | `Sem4Visitor.java`, `Sem5Visitor.java` | `asst4/starter4/starter4/` |
| 5 | `CG1Visitor.java`, `CG2Visitor.java`, `CG3Visitor.java` | `asst5/starter5/` |

### Instructor-provided infrastructure files:

The following packages are needed from the asst5 starter (which is the most complete version):

- `syntaxtree/` — AST node classes
- `errorMsg/` — Error/message handling
- `parse/` — Parse table infrastructure
- `main/` — Main.java entry point
- `visitor/` — Visitor interfaces (Visitor.java, Visitor2.java)
- Helper visitors: `ASTGenVisitor.java`, `EqualVisitor.java`, `InitPredefined.java`, `PrettyPrintVisitor.java`, `TreeDrawerVisitor.java`, `ConstEvalVisitor.java`
- `CodeStream.java`, `VtableGenerator.java`, `Lib.java`, `RunMain.java`
- Parse table definitions (MJGrammar.java/MJScanner.java token/grammar specs)

Mark all instructor-provided files with a comment at the top: `// Provided by instructor — infrastructure code`

### Build system

- Use the **Makefile** from `asst5/starter5/` as the template and adapt it
- Target **Java 21** (`-source 21 -target 21`)
- Keep wrangLR.jar (the parser generator) — it is permanently needed
- The Makefile should have targets for: `all`, `compile`, `run` (with file arg), `clean`, `test`

### Test files

- Include ALL test directories from asst5: `tests/`, `tests5a/`, `tests5b/`, `testsA/`
- Cherry-pick these from earlier assignments:
  - From asst4: the type-error test files (`TypeErrors.java`, `TypeErrorsOutput.java`, `WarningsTest.java`, `TestCast*.java`, `TestInstanceOf*.java`, `Error*.java`, `TestBinaryOpErrors.java`, `TestArrayIdxMismatch.java`, `TestNegArrayIndex.java`, `TestNegArraySize.java`, `Unreachable.java`)
  - From asst1: the `testCases/` directory (lexer edge cases — useful for regression testing)
  - Organize into `tests/semantic/` and `tests/lexer/` subdirectories

### Binaries and .gitignore

- Keep `wrangLR.jar` in the repo (required for building)
- Keep `RIPS.jar`, `rips-cli.exe`, `mips_engine.dll`, `mjLib-rust.asm` **temporarily** for testing, then **remove** them
- Include `mjLib-rust.asm` in the repo (runtime library needed for output assembly)
- Create a `.gitignore` that excludes: `*.class`, `RIPS.jar`, `rips-cli*`, `mips_engine.dll`, `.git/`, IDE files, OS files

### Git

- Initialize a git repo in `COMPILER/`
- Create a proper `.gitignore`
- Make an initial commit after everything is working

### Comment style

- **Minimal comments in code.** Do NOT add heavy comments to the student's files. Only add brief clarifying comments where the purpose of a method or block is genuinely non-obvious.
- The focus is on a **well-written README.md**, not inline comments.

### README.md — this is critical

The README should be thorough and employer-friendly. Include all four sections:

1. **Architecture Overview** — Diagram/flowchart showing the full pipeline:
   Lexer (TokenGrammar) → Parser (MJGrammar) → Semantic Analysis (Sem1-5Visitors: declaration checking, name resolution, initialization, type checking, constant eval) → Code Generation (CG1-3Visitors: offset computation, string literals, MIPS emission)
   Briefly describe each phase and what the student implemented vs. what was provided infrastructure.

2. **Usage Walkthrough** — Step-by-step instructions:
   - Prerequisites (Java 21, make)
   - How to clone and build (`make all`)
   - How to compile a MiniJava program to MIPS (`make run FILE=tests/TestFile.java`)
   - How to run the output with RIPS or SPIM
   - How to run the test suite (`make test`)
   - Include exact commands and expected output.

3. **Language Features** — What MiniJava subset is supported:
   - Types: int, boolean, char, String, arrays, classes with inheritance
   - Control flow: if/else, while, for, do-while, switch/case, break
   - OOP: classes, fields, methods, inheritance, method overriding, instanceof, casts
   - Arrays: creation, access, length, multi-dimensional
   - Expressions: binary ops, unary ops, method calls, field access, array lookup
   - Predefined classes: Object, String, Lib (I/O), RunMain

4. **Example Output** — Show a sample MiniJava program, the command to compile it, and the generated MIPS assembly. Include the program's console output when run.

### Execution Plan

1. Create the directory structure inside `COMPILER/` modeled after asst5's layout
2. Copy the student's files from each assignment into the appropriate packages
3. Copy all instructor-provided infrastructure `.java` files from asst5 (the most complete version), adding the attribution comment
4. Copy `wrangLR.jar` and `mjLib-rust.asm`
5. Create the Makefile (adapted from asst5)
6. Copy test files (asst5 tests + cherry-picked from asst1/asst4)
7. Compile with `make all` and fix any compilation errors
8. Test with several MiniJava programs and fix any runtime errors
9. Temporarily copy `RIPS.jar` and binaries for testing, verify output is correct, then remove them
10. Create `.gitignore` and `README.md`
11. Init git repo and make initial commit

### Important constraints

- Do NOT use the precompiled `.class` files the instructor provided in each assignment's starter folder. Compile everything from `.java` source.
- The `.java` infrastructure files may differ slightly between assignments. Always use the version from **asst5** (the latest/most complete), EXCEPT for the student's own files which come from their respective assignments.
- If the student's code references classes or methods that don't match the asst5 infrastructure, update the student's code to be compatible (not the other way around).
- If you encounter bugs, fix them and note what you changed and why in a `CHANGES.md` file.
