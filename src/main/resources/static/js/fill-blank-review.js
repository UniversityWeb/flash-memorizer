// Wait for the DOM content to load before executing the script
document.addEventListener("DOMContentLoaded", function () {

    var nextButtons = document.querySelectorAll('.btn-next');
    nextButtons.forEach(function (nextButton) {
        nextButton.addEventListener("click", function () {

            var card = this.closest('.outer-card');

            if (card) {
                // Calculate the height of the 'outer-card' element, including margins
                var cardStyleHeight = window.getComputedStyle(card);
                var cardHeight = card.offsetHeight + parseInt(cardStyleHeight.marginTop) + parseInt(cardStyleHeight.marginBottom);

                // Scroll the window down by the height of the 'outer-card' element with smooth behavior
                window.scrollBy({ top: cardHeight, behavior: 'smooth' });
            }
        });
    });

    function updateUserChoices() {
        var cards = document.querySelectorAll('.outer-card');

        cards.forEach(function (card) {
            var inputs = card.querySelectorAll('input[type="text"]');
            var cardInputValue = "";

            inputs.forEach(function (input) {
                // Get the input with no extra spaces
                var inputValue = input.value.trim();

                if (inputValue === "") {
                    cardInputValue += "null-input-value ";
                } else {
                    cardInputValue += inputValue + " ";
                }
            });

            cardInputValue = cardInputValue.trim();
            // Replace multiple spaces with a single space
            cardInputValue = cardInputValue.replace(/\s+/g, ' ');

            var hiddenInput = card.querySelector('.hidden-user-choices');
            hiddenInput.value = cardInputValue;
        });
    }

    var submitButton = document.querySelector('.btn-submit');
    submitButton.addEventListener("click", function () {
        updateUserChoices();
    });
});