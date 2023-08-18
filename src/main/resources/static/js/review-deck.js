var carousel = document.getElementById('carouselExampleCaptions');
carousel.addEventListener('slide.bs.carousel', function (event) {
    var currentSlide = event.to +1;
    var totalSlides = carousel.querySelectorAll('.carousel-item').length;
    var progress = (currentSlide / totalSlides) * 100;
    var progressBarIndicator = document.getElementById('progress-indicator');
    var progressBar = document.getElementById('progress-bar');
    var progressNumber = document.getElementById("progress-number");
    progressBar.style.width = progress + '%';
    progressBar.setAttribute('aria-valuenow', progress.toString());
    progressNumber.innerHTML = currentSlide + '/' + totalSlides;
});

function flipCard(card) {
  card.querySelector('.flip-card-inner').classList.toggle('flip-animation');
}
