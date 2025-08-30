function processData(){
    let data=[1,2,3,4];
    let result = data.forEach(item => {
        if(item%2 == 0){
            return item*10;
        }
    });
    return result;
}

processData();