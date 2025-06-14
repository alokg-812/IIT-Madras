function add1(x,y) {
    return x+y;
}
console.log(add1(2,3));


let add2 = (x,y) => x+y;
console.log(add2(2,3));

let add3 = function(x,y) {return x+y};
console.log(add3(2,3));

console.log(typeof add1); //Regular Function
console.log(typeof add2); //Anonymous Function
console.log(typeof add3); // IIFE Function


