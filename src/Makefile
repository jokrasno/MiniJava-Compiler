JC = javac
JFLAGS = -cp ".;wrangLR.jar" -source 21 -target 21

JAVA_FILES = errorMsg/*.java syntaxtree/*.java visitor/*.java main/*.java parse/MJGrammar.java parse/MJGrammarParseTable.java Lib.java

all: compile

compile:
	$(JC) $(JFLAGS) $(JAVA_FILES)

run: compile
	java -cp ".;wrangLR.jar" main.Main $(FILE)

test: compile
	@echo "=== Running test suite ==="
	@for f in ../tests/*.java; do \
		echo "--- $$f ---"; \
		java -cp ".;wrangLR.jar" main.Main "$$f" 2>&1 | tail -1; \
	done

clean:
	rm -f errorMsg/*.class
	rm -f main/*.class
	rm -f syntaxtree/*.class
	rm -f visitor/*.class
	rm -f parse/MJGrammar.class
	rm -f parse/MJGrammarParseTable*.class
	rm -f Lib.class
