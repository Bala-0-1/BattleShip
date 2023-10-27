data = {username: "username", password: "password1234569"}


async function loginUserNameAndPassword() {

    try{

        const response = await fetch('/BattleShip/ServletTest', {
            method: 'POST', 
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(data)
        });
        if (!response.ok) {
            throw new Error("Yeah could not find shit.");
        }

		console.log(response);
        return response.json;

    } catch (error) {

        console.log('There was an error with the fetch operation: ', error);
        return null;

    }


}

async function getMethod(){
    const response = await fetch("/BattleShip/ServletTest"); 
    if (!response.ok){
        console.log("Error!!1")
    }
    else {
        console.log(response.json);
    }
}

getMethod();
console.log(loginUserNameAndPassword());