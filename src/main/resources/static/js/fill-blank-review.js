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
});