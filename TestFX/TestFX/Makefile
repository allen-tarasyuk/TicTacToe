JAVAC=javac --source-path src/testfx -d bin/testfx
JAVA=java -cp bin/testfx
MODULES=--module-path lib/ --add-modules javafx.controls,javafx.fxml
SRC=src/testfx/*.java 

all:
	$(JAVAC) $(MODULES) $(SRC)

run:
	$(JAVA) $(MODULES) src/testfx/Main.java