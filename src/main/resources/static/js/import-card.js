const listCards = document.getElementById("listCards");
const textarea = document.getElementById("document_input");
const none_contain = document.getElementById("none_contain");
//input
const input_custom1 = document.getElementById("input_custom1");
const input_custom2 = document.getElementById("input_custom2");
  // Lấy danh sách tất cả các nút radio trong cùng một nhóm (cùng tên)
var radioButtons1 = document.getElementsByName("betweenTerm_decs");
var radioButtons2 = document.getElementsByName("betweenCards");



function changeBorderColor(event) {
    const textarea = event.target;
    const hasInput = textarea.value.trim() !== ''; // Kiểm tra nếu có nhập liệu
    
    // Thay đổi màu border tùy thuộc vào có nhập liệu hay không
    if (hasInput) {
      textarea.style.border = '4px solid #ff983a'; // Màu border khi có nhập liệu
    } else {
      textarea.style.border = '2px solid #353b3e'; // Màu border khi không có nhập liệu
    }
}

  textarea.addEventListener("keydown", function (event) {
    // Nếu phím nhấn là Tab, ngăn trình duyệt xử lý sự kiện mặc định
    if (event.key === "Tab") {
      event.preventDefault();

      // Thêm ký tự tab vào vị trí con trỏ hiện tại trong textarea
      const { selectionStart, selectionEnd, value } = event.target;
      event.target.value =
        value.substring(0, selectionStart) + "\t" + value.substring(selectionEnd);

      // Di chuyển con trỏ đến vị trí tiếp theo sau khi thêm tab
      event.target.selectionStart = event.target.selectionEnd = selectionStart + 1;
    }
  });
  
  // Định nghĩa hàm kiểm tra số chẵn
  function importWithText(content,betweenTermAndDesc,betweenCards) {
    const arrLine = content.split(betweenCards);
    var arrTerm =[];
    var arrDesc = [];
    var nTerm =0;
    var nDesc =0;
    let numberCard = 0;
    for (var line of arrLine) {
      const arr=line.split(betweenTermAndDesc);
      arrTerm[nTerm++]=arr[0];
      arrDesc[nDesc++]=arr[1];
      numberCard = numberCard + 1;
      
    }
    for (let i = 0; i < numberCard; i++){
      console.log(arrTerm[i]+"----"+arrDesc[i]);
      var li = document.createElement("li");
      li.className="card_item";
      li.innerHTML = `
      <div class="number_of_item">
      <h3>${i+1}</h3>
    </div>
    <div class="item_content">
      <span class="item_txt">${arrTerm[i]}</span>
      <div class="line"></div>
      <h4>TERM</h4>
    </div>
    <div class="item_content">
      <span class="item_txt">${arrDesc[i]}</span>
      <div class="line"></div>
      <h4>DEFINITION</h4>
    </div>
  `;
      listCards.appendChild(li);
    }
  }

  // Định nghĩa hàm xử lý sự kiện khi textarea thay đổi
  function handleTextareaChange() {
    // Lấy nội dung của textarea
    const text = textarea.value;
    listCards.innerHTML = "";
    var betweenTermAndDesc = ""; var betweenCards = "";
    
    for (var i = 0; i < 3; i++) {  
      if (radioButtons1[i].checked) {
        if(i!=2)
        betweenTermAndDesc = radioButtons1[i].value;
        else
        betweenTermAndDesc = input_custom1.value;
        break;
      }
    }
    for (var i = 0; i < 3; i++) {
      if (radioButtons2[i].checked) {
        if(i!=2)
        betweenCards = radioButtons2[i].value;
        else
        betweenCards = input_custom2.value;
        break;
      }
    }
   importWithText(text,betweenTermAndDesc,betweenCards);
 //   importWithText(text,"\t","\n");
    if(textarea.value == ""){
      listCards.innerHTML = "";
    }
    
  }

  // Gán sự kiện "input" để lắng nghe sự thay đổi của textarea
  textarea.addEventListener("input", handleTextareaChange);

  var liElements = listCards.getElementsByTagName("li");

  // Đếm số lượng phần tử li
  var liCount = liElements.length;
  if(liCount==0){
    none_contain.style.display = "flex"
  }
    

//even
function handleRadioChange() {
  // Duyệt qua danh sách nút radio để xem nút nào được chọn
  var str1=""; var str2=""; var str3="";
  var value1 = ""; var value2 = "";
  for (var i = 0; i < 3; i++) {  
    if (radioButtons1[i].checked) {
      if(i!=2)
      value1 = radioButtons1[i].value;
      else
      value1 = input_custom1.value;
      str1="Word 1"+value1+"Definition 1";
      str2="Word 2"+value1+"Definition 2";
      str3="Word 3"+value1+"Definition 3";
      break;
    }
  }
  for (var i = 0; i < 3; i++) {
    if (radioButtons2[i].checked) {
      if(i!=2)
      value2 = radioButtons2[i].value;
      else
      value2 = input_custom2.value;
      break;
    }
  }
  textarea.placeholder=str1+value2+str2+value2+str3;
}

//static
textarea.placeholder="Word 1"+radioButtons1[0].value+"Definition 1"+radioButtons2[0].value+"Word 2"+radioButtons1[0].value+"Definition 2"+radioButtons2[0].value+"Word 3"+radioButtons1[0].value+"Definition 3";