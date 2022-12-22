let loadBooksBtn = document.getElementById('loadBooks');

loadBooksBtn.addEventListener('click', onLoadBooks);

function onLoadBooks(event) {
    event.preventDefault();
    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    let bookContainer = document.getElementById('authors-container');
    bookContainer.innerHTML = '';

    //From Postman snippets
    fetch("http://localhost:8080/api/books", requestOptions)
        .then(response => response.json())
        .then(json => json.forEach(book=>{

            let row = document.createElement('tr');
            let titleCol = document.createElement('td');
            let authorCol = document.createElement('td');
            let isbnCol = document.createElement('td');
            let actionCol = document.createElement('td');
            let editBtn = document.createElement('button');
            let deleteBtn = document.createElement('button');

            titleCol.textContent = book.title;
            authorCol.textContent = book.author.name;
            isbnCol.textContent = book.isbn;
            editBtn.textContent = 'Edit';
            deleteBtn.textContent = 'Delete';

            actionCol.appendChild(editBtn);
            actionCol.appendChild(deleteBtn);

            row.appendChild(titleCol)
            row.appendChild(authorCol)
            row.appendChild(isbnCol)
            row.appendChild(actionCol)
            bookContainer.appendChild(row);
        }))
        .catch(error => console.log('error', error));
}