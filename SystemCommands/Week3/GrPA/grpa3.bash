script() { 
    echo "Shell:$(readlink /proc/$$/exe)|PID:$$|Flags:$-"
}