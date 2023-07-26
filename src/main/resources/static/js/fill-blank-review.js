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
});
