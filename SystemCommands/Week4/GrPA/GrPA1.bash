script() {
  grep -viE '\b(a|an|the)\b' poem | grep -v '^$' | wc -l
}
