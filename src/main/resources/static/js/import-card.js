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

  // Lấy tham chiếu đến textarea
  const textarea = document.getElementById("document_input");

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
    const arrTerm =[];
    const arrDesc = [];
    let numberCard = 0;
    for (const line of arrLine) {
      const arr=line.split(betweenTermAndDesc);
      arrTerm.push=arr[0];
      arrDesc.push=arr[1];
      numberCard = numberCard + 1;
    }
    const listCards = document.getElementById("listCards");
    for (let i = 0; i < numberCard; i++){
      const li = document.createElement("li");
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
    importWithText(text,'\t','\n');
  }

  // Gán sự kiện "input" để lắng nghe sự thay đổi của textarea
  textarea.addEventListener("input", handleTextareaChange);
