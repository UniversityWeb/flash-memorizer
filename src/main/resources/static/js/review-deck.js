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


//modal
// modal for new proj
var modal_share = document.getElementById("modalshare");
function openModalShare() {
    modal_share.style.display = "flex";
}

function closeModalShare() {
    modal_share.style.display = "none";
}


const inputUserName = document.getElementById('inputUserName');
const listUsers = document.getElementById('listUsers');
var currentURL = window.location.href;
var matches = currentURL.match(/\/(\d+)$/);
var deck_ID = matches[1];
//
//
function GetUsers(data,callback){
    const GetUserNameUrl = 'http://localhost:8000/users/get-by-username';
    var options = {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json' // Đảm bảo đặt tiêu đề Content-Type là application/json
        }
    };

    fetch(GetUserNameUrl, options)
        .then(function(response) {
            return response.json(); // Trả về kết quả từ .json() để đảm bảo kết quả được chuyển tiếp
        })
        .then(callback)
        .catch(function(error) {
            console.log('Error');
        });
}

function  renderUser(user){
    var li = document.createElement("li");
    li.className="user__item";
    li.innerHTML = `
     <img src="https://trinhvantuyen.com/wp-content/uploads/2022/05/f410c21a9ac9b699e1ed83ff66e24cba.jpg" class="user__img">
     <div class="user__info">
     <p class="user__name">${user.username}</p>
     <p>${user.email}</p> 
     </div>
  `;
    listUsers.appendChild(li);
    li.addEventListener('click',function (){
        var data= {
            deck:{
                id:deck_ID
            },
            recipient:{
                id:user.id
            },
        }
        shareDeck(data);
    });
}

inputUserName.addEventListener('change', (event) => {
    listUsers.innerHTML='';
    const newValue =  event.target.value;
    GetUsers(newValue,renderUser);
});

//
function shareDeck(data,callback){
    const ShareDeckUrl = 'http://localhost:8000/sharedDecks/add';
    var options = {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json' // Đảm bảo đặt tiêu đề Content-Type là application/json
        }
    };
    fetch(ShareDeckUrl, options)
        .then(function(response) {
            return response.json(); // Trả về kết quả từ .json() để đảm bảo kết quả được chuyển tiếp
        })
        .then(callback)
        .catch(function(error) {
            console.log('Error');
        });
}
