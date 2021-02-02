#~/bash
echo "Compiling $1.java"
javac "$1.java"
echo "Executing $1"
java "$@"
