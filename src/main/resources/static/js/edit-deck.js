var deleteButtons = document.querySelectorAll(".delete-button");
deleteButtons.forEach(function(button) {
    button.addEventListener("click", function() {
        var listItem = button.closest("li");
        listItem.remove();
    });
});

function addCard() {
    const cardList = document.getElementById('cardList');
    const index = cardList.childElementCount; // Get the current number of cards in the list

    const li = document.createElement('li');
    li.classList.add('list-group-item');

    const cardBody = document.createElement('div');
    cardBody.classList.add('card-body', 'mb-3', 'position-relative');

    const formatBar = document.createElement('div');
    formatBar.classList.add('format-bar', 'd-flex', 'justify-content-between');

    const buttonGroup = document.createElement('div');
    buttonGroup.innerHTML = `
        <button type="button" class="btn btn-outline-primary btn-sm">Bold</button>
        <button type="button" class="btn btn-outline-primary btn-sm">Italic</button>
    `;

    const deleteButton = document.createElement('button');
    deleteButton.classList.add('btn', 'btn-outline-danger', 'btn-sm', 'delete-button');
    deleteButton.innerHTML = '<i class="fas fa-trash"></i>';
    // You need to add the correct URL mapping for delete in the th:href attribute of deleteButton

    formatBar.appendChild(buttonGroup);
    formatBar.appendChild(deleteButton);

    const hiddenInput = document.createElement('input');
    hiddenInput.type = 'hidden';
    hiddenInput.name = 'cards[' + index + '].id'; // Set the name attribute to match the th:field expression

    const formRow = document.createElement('div');
    formRow.classList.add('form-row', 'mt-3');

    const colTerm = document.createElement('div');
    colTerm.classList.add('col');

    const labelTerm = document.createElement('label');
    labelTerm.textContent = 'Term';

    const textareaTerm = document.createElement('textarea');
    textareaTerm.classList.add('form-control');
    textareaTerm.name = 'cards[' + index + '].term'; // Set the name attribute to match the th:field expression
    textareaTerm.setAttribute('rows', '3');
    textareaTerm.setAttribute('minlength', '1');
    textareaTerm.setAttribute('maxlength', '100');
    textareaTerm.required = true;

    colTerm.appendChild(labelTerm);
    colTerm.appendChild(textareaTerm);

    const colDesc = document.createElement('div');
    colDesc.classList.add('col');

    const labelDesc = document.createElement('label');
    labelDesc.textContent = 'Description';

    const textareaDesc = document.createElement('textarea');
    textareaDesc.classList.add('form-control');
    textareaDesc.name = 'cards[' + index + '].desc'; // Set the name attribute to match the th:field expression
    textareaDesc.setAttribute('rows', '3');
    textareaDesc.setAttribute('minlength', '1');
    textareaDesc.setAttribute('maxlength', '1000');
    textareaDesc.required = true;

    colDesc.appendChild(labelDesc);
    colDesc.appendChild(textareaDesc);

    formRow.appendChild(colTerm);
    formRow.appendChild(colDesc);

    cardBody.appendChild(formatBar);
    cardBody.appendChild(hiddenInput);
    cardBody.appendChild(formRow);

    li.appendChild(cardBody);
    cardList.appendChild(li);
}

function init() {
    const addCardButton = document.getElementById('addCardButton');
    addCardButton.addEventListener('click', addCard);
}

document.addEventListener('DOMContentLoaded', init);
