PO_UILIB_DIR=/home/cravid/videos/po-uilib-202109221024
PO_GGC_CORE_DIR=../../ggc-core
JARNAME=ggc-app

all:
	(cd src; javac -cp $(PO_UILIB_DIR)/po-uilib.jar:$(PO_GGC_CORE_DIR)/ggc-core.jar -encoding UTF-8 `find . -name \*.java`)
	(cd src; jar cf ../$(JARNAME).jar `find . -name \*.class -o -name \*.java` )

clean:
	$(RM) $(JARNAME).jar `find . -name \*.class`
