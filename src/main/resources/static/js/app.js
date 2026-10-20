
document.querySelectorAll('.change-btn-design').forEach(select => {
    select.addEventListener('input' , () => {
        //                この↓select.valueは操作した後に選ばれる値が入っている。
        const isSuccess = select.value === 'true';// 比べた結果が入る。
        //                                           == から === に変更
        select.classList.toggle('text-bg-success', isSuccess);
        select.classList.toggle('text-bg-warning', !isSuccess);
    });
});




function checkConfirm(){
    // confirmダイアログを表示して、値によって処理を変える
    const result = window.confirm("削除してもよろしいですか？");

    if(result){
        return true;
    }else{
        return false;
    }

    // return result;↑は冗長
}

