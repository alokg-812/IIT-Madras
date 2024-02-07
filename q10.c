#include <stdio.h>
int slen(char str[]) {
    if(str[0]=='\0') return 0;
    return 1 + slen(str + 1);
}
int main() {
    char str[100];
    printf("Enter the string: ");
    scanf("%s",&str);
    printf("%d",slen(str));
    return 0;
}
