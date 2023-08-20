document.addEventListener("DOMContentLoaded", function () {
    // Scroll to the selected option's card
    var options = document.querySelectorAll('input[type="radio"]');
    options.forEach(function (option) {
        option.addEventListener('click', function () {
            var card = document.querySelector('.outer-card');

            var cardStyleHeight = window.getComputedStyle(card);
            var cardHeight = card.offsetHeight + parseInt(cardStyleHeight.marginTop) + parseInt(cardStyleHeight.marginBottom);

            window.scrollBy({ top: cardHeight, behavior: 'smooth' });
        });
    });
});