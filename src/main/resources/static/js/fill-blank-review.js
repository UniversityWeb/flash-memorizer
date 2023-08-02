document.addEventListener("DOMContentLoaded", function () {
    var nextButtons = document.querySelectorAll('.btn-next');
    nextButtons.forEach(function (nextButton) {
        nextButton.addEventListener("click", function () {
            var card = this.closest('.outer-card');

            if (card) {
                var cardStyleHeight = window.getComputedStyle(card);
                var cardHeight = card.offsetHeight + parseInt(cardStyleHeight.marginTop) + parseInt(cardStyleHeight.marginBottom);
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
                var inputValue = input.value.trim();
                            if (inputValue === "") {
                                cardInputValue += "null-input-value ";
                            } else {
                                cardInputValue += inputValue + " ";
                            }
            });

            cardInputValue = cardInputValue.trim();
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
