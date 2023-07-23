var deleteButtons = document.querySelectorAll(".delete-button");
deleteButtons.forEach(function(button) {
    button.addEventListener("click", function() {
        var listItem = button.closest("li");
        listItem.remove();
    });
});
