var options = document.querySelectorAll('input[type="radio"]');
options.forEach(function(option) {
    // Attach a 'click' event listener to each radio button
    option.addEventListener('click', function() {
        var card = document.querySelector('.outer-card');
        
        // Calculate the height of the 'outer-card' element, including margins
        var cardStyleHeight = window.getComputedStyle(card);
        var cardHeight = card.offsetHeight + parseInt(cardStyleHeight.marginTop) + parseInt(cardStyleHeight.marginBottom);

        // Scroll the window down by the height of the 'outer-card' element with smooth behavior
        window.scrollBy({ top: cardHeight, behavior: 'smooth' });
    });
});

const radioButtons = document.querySelectorAll('input[type="radio"]');
radioButtons.forEach((radio) => {
    // Attach a 'change' event listener to each radio button
    radio.addEventListener('change', (event) => {
        const selectedOptionText = event.target.nextElementSibling.textContent;
        const cardReviewIndex = event.target.getAttribute('data-card-review-index');
        const hiddenInput = document.getElementById('cardReviewIndexChoose' + cardReviewIndex);

        // Update the value of the hidden input with the trimmed text content of the selected option
        hiddenInput.value = selectedOptionText.trim();
    });
});