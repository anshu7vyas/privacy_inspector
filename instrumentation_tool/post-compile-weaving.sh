#!/bin/bash

TEMP_DIR=$ASPECTJ_HOME/target_classes/

CLASSES_DIR=$1

CLASSPATH=$ASPECTJ_HOME/lib/aspectjrt.jar:$ASPECTJ_HOME/lib/aspectjtools.jar:$2:$ASPECTJ_HOME/lib/icu4j.jar:$3

mkdir $ASPECTJ_HOME/temporary_classes/

echo "compiling aspects"
#TODO: compile all java files in dir $CUSTOM_ASPECT_HOME
#find $CUSTOM_ASPECT_PATH -type f -name "*.java" -exec javac -classpath $CLASSPATH -g:vars -d $CLASSES_DIR {} \;
javac -cp $CLASSPATH -g:vars /home/av7/ANDROID/WD1/instrumentation_tool/src/aspectj/HttpStringAspect.java /home/av7/ANDROID/WD1/instrumentation_tool/src/aspectj/LocationAspect.java /home/av7/ANDROID/WD1/instrumentation_tool/src/android/location/Location.java /home/av7/ANDROID/WD1/instrumentation_tool/src/aspectj/Logging.java /home/av7/ANDROID/WD1/instrumentation_tool/src/aspectj/IMEIAspect.java /home/av7/ANDROID/WD1/instrumentation_tool/src/aspectj/ByteConversions.java /home/av7/ANDROID/WD1/instrumentation_tool/src/aspectj/StringEncodingDetector.java /home/av7/ANDROID/WD1/instrumentation_tool/src/aspectj/SlidingBuffer.java /home/av7/ANDROID/WD1/instrumentation_tool/src/aspectj/HttpDoubleAspect.java /home/av7/ANDROID/WD1/instrumentation_tool/src/aspectj/AspectConstants.java -d $ASPECTJ_HOME/temporary_classes/

rm -rf $ASPECTJ_HOME/temporary_classes/android/location/Location.class
cp -r $ASPECTJ_HOME/temporary_classes/* $CLASSES_DIR

#cp -r /home/av7/ANDROID/WD1/instrumentation_tool/classes/aspectj $CLASSES_DIR

rm -rf $ASPECTJ_HOME/temporary_classes/

echo "Weaving aspect..."
 
#JAVA_OPTS="-Xmx2048M" ajc  -source 1.5 -inpath $CLASSES_DIR/ -aspectpath $CUSTOM_ASPECT_PATH -d $TEMP_DIR
java -cp $CLASSPATH org.aspectj.tools.ajc.Main -Xmx2048M -source 1.5 -inpath $CLASSES_DIR/ -aspectpath $CUSTOM_ASPECT_PATH -d $TEMP_DIR 

if [[ $CLASSES_DIR == *"framework_intermediates"* ]]; then 
	rm -rf $TEMP_DIR/aspectj/; 
 fi

#rm -rf $TEMP_DIR/

echo "Replacing the modified classes..."
cp -rf $TEMP_DIR/* $CLASSES_DIR/

rm -rf $TEMP_DIR/

# copy classes in aspectjrt.jar 
cp -r $ASPECTJ_HOME/extracted_classes/* $CLASSES_DIR/


