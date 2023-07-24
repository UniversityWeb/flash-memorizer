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