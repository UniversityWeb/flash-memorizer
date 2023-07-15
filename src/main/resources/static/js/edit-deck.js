document.getElementById("addCardBtn").addEventListener("click", function() {
    var cardList = document.getElementById("cardList");

    // Create a new list item
    var listItem = document.createElement("li");
    listItem.classList.add("list-group-item");

    // Create a new card body
    var cardBody = document.createElement("div");
    cardBody.classList.add("card-body", "position-relative");

    // Create the format bar
    var formatBar = document.createElement("div");
    formatBar.classList.add("format-bar", "d-flex", "justify-content-between");

    // Create formatting buttons
    var formatButtons = document.createElement("div");

    var boldButton = document.createElement("button");
    boldButton.type = "button";
    boldButton.classList.add("btn", "btn-outline-primary", "btn-sm", "mr-1");
    boldButton.textContent = "Bold";

    var italicButton = document.createElement("button");
    italicButton.type = "button";
    italicButton.classList.add("btn", "btn-outline-primary", "btn-sm");
    italicButton.textContent = "Italic";

    // Create the delete button
    var deleteButton = document.createElement("button");
    deleteButton.type = "button";
    deleteButton.classList.add("btn", "btn-outline-danger", "btn-sm", "delete-button");
    deleteButton.innerHTML = '<i class="fas fa-trash"></i>';

    // Add event listener to delete button
    deleteButton.addEventListener("click", function() {
        listItem.remove();
    });

    // Create the form row
    var formRow = document.createElement("div");
    formRow.classList.add("form-row", "mt-3");

    // Create the term column
    var termCol = document.createElement("div");
    termCol.classList.add("col");

    // Create the term label
    var termLabel = document.createElement("label");
    termLabel.textContent = "Term";

    // Create the term textarea
    var termTextarea = document.createElement("textarea");
    termTextarea.classList.add("form-control");
    termTextarea.name = "Term";
    termTextarea.rows = 3;

    // Create the description column
    var descCol = document.createElement("div");
    descCol.classList.add("col");

    // Create the description label
    var descLabel = document.createElement("label");
    descLabel.textContent = "Description";

    // Create the description textarea
    var descTextarea = document.createElement("textarea");
    descTextarea.classList.add("form-control");
    descTextarea.name = "Description";
    descTextarea.rows = 3;

    // Append elements to the DOM
    formatButtons.appendChild(boldButton);
    formatButtons.appendChild(italicButton);

    formatBar.appendChild(formatButtons);
    formatBar.appendChild(deleteButton);

    termCol.appendChild(termLabel);
    termCol.appendChild(termTextarea);

    descCol.appendChild(descLabel);
    descCol.appendChild(descTextarea);

    formRow.appendChild(termCol);
    formRow.appendChild(descCol);

    cardBody.appendChild(formatBar);
    cardBody.appendChild(formRow);

    listItem.appendChild(cardBody);
    cardList.appendChild(listItem);
});

var deleteButtons = document.querySelectorAll(".delete-button");
deleteButtons.forEach(function(button) {
    button.addEventListener("click", function() {
        var listItem = button.closest("li");
        listItem.remove();
    });
});