const routeId = document.getElementById('routeId').value
const commentForm = document.getElementById('commentForm')
commentForm.addEventListener('submit', handleForSubmission)

const commentContainer = document.getElementById('commentCtnr')

const csrfHeaderName = document.head.querySelector('[name_csrf_header]').content
const csrfHeaderValue = document.head.querySelector('[name= _csrf]').content

async function handleForSubmission(event){
    event.preventDefault()

    const message = document.getElementById('message').value

    fetch('http://localhost:8080/api/${routeId}/comments', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            [csrfHeaderName]: csrfHeaderValue
        },
        body: JSON.stringify({
            message: messageVal
        })
    }).then(res => res.json())
        .then(data => {
            document.getElementById('message').value = ""
            commentContainer.innerHtml += commentHtml(data)
        })
}

function commentContainer(comment){
    let commentHtml = '<div>\n'
    commentHtml += '<h4>${comment.authorName}</h4>\n'
    commentHtml += '<p>${comment.message}</p>\n'
    commentHtml += '</div>\n'

    return commentHtml
}

fetch('http://localhost:8080/api/${routeId)/comments',{
    headers: {
        "Accept": "application/json"
    }
}).then(res => res.json())
    .then(data => {
        for(let comment of data){
            commentContainer.innerHtml += commentAsHtml(comment)
        }
    })
