#!/bin/bash

# check if variables are set
if [ -z "$ANDROID_HOME" ]; then
    echo "ANDROID_HOME is not set"
   return
fi

if [ -z "$CUSTOM_ASPECT_PATH" ]; then
    echo "CUSTOM_ASPECT_PATH is not set"
   return 1
fi

rm -rf $ANDROID_HOME/aspectj
mkdir $ANDROID_HOME/aspectj
export ASPECTJ_HOME=$ANDROID_HOME/aspectj

#copy aspectj libraries to path: $ANDROID_SOURCE/aspectj/lib

cp -r lib/ $ASPECTJ_HOME/
cp post-compile-weaving.sh $ASPECTJ_HOME/

mkdir $ASPECTJ_HOME/target_classes 

# apply patch of definitions.mk
#patch $ANDROID_HOME/build/core/definitions.mk < definitions.patch

mkdir $ASPECTJ_HOME/extracted_classes
unzip $ASPECTJ_HOME/lib/aspectjrt.jar -d $ASPECTJ_HOME/extracted_classes > /dev/null
unzip $ASPECTJ_HOME/lib/icu4j.jar -d $ASPECTJ_HOME/extracted_classes > /dev/null
