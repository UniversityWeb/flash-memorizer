var options = document.querySelectorAll('input[type="radio"]');
    options.forEach(function(option) {
        option.addEventListener('click', function() {
            var card = document.querySelector('.outer-card');
            var cardStyleHeight = window.getComputedStyle(card);
            var cardHeight = card.offsetHeight + parseInt(cardStyleHeight.marginTop) + parseInt(cardStyleHeight.marginBottom);

            window.scrollBy({ top: cardHeight, behavior: 'smooth' });
        });
    });

const radioButtons = document.querySelectorAll('input[type="radio"]');
radioButtons.forEach((radio) => {
    radio.addEventListener('change', (event) => {
        const selectedOptionText = event.target.nextElementSibling.textContent;
        const cardReviewIndex = event.target.getAttribute('data-card-review-index');
        const hiddenInput = document.getElementById('cardReviewIndexChoose' + cardReviewIndex);

        hiddenInput.value = selectedOptionText.trim();
    });
});