const routeId = document.getElementById('routeId').value
const commentForm = document.findElementById('commentForm')
commentForm.addEventListener('submit', handleForSubmission)

async function handleForSubmission(event){
    event.preventDefault()

    const message = document.getElementById('message').value

    fetch('http://localhost:8080/api/${routeId}/comments', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accepts': 'application/json'
        },
        body: JSON.stringify({
            message: messageVal
        })
    }).then(res => res.json())
        .then(data => {
            console.log(data)
        })
}