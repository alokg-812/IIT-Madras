script() { 

if [ -z "$1" ]; then
    echo "Usage: $0 <destination_directory>"
    exit 1
fi

dest_dir="$1"
source_dir="perf_folder"

mkdir -p "$dest_dir"

for file in "$source_dir"/*perf*.c; do
    [ -e "$file" ] || continue

    filename=$(basename "$file")
    newname="program_$filename"
    mv "$file" "$dest_dir/$newname"
done


} 