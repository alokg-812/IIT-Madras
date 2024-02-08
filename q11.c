#include<stdio.h>
#include<string.h>
int pal(char str[]) {
    int start = 0, end = (strlen(str)-1);
    
} 
int main () {
    char str[100];
    printf("Eneter the string: ");
    scanf("%s",&str);
    if(pal(str)) printf("%s is pal...",str);
    printf("%s is not pal...",str);
    return 0;
}