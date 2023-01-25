var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });

        $('#btn-search').on('click' , function () {
            _this.searchPost();
        });

        $('#btn-comment-save').on('click', function (){
            _this.commentSave();
        });

        $('btn-comment-delete').on('click', function(){
            _this.commentDelete();
        });



        document.querySelectorAll('#btn-comment-update').forEach(function (item) {
            item.addEventListener('click', function () { // 버튼 클릭 이벤트 발생시
                const form = this.closest('form'); // btn의 가장 가까운 조상의 Element(form)를 반환 (closest)
                _this.commentUpdate(form); // 해당 form으로 업데이트 수행
            });
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var isUserPost = $('#isUserPost').val();
        var isSearchPost = $('#isSearchPost').val();
        var searchContent = $('#searchContent').val();
        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            if (typeof isUserPost != "undefined"){
                window.location.href = '/user/posts/inquiry/'+id;
            }
            else if (typeof isSearchPost != "undefined")
                window.location.href = '/search/posts/inquiry/'+id+'?searchContent='+searchContent;
            else
                window.location.href = '/posts/inquiry/'+id;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();
        var isUserPost = $('#isUserPost').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            if (isUserPost == true)
                window.location.href = '/user';
            else
                window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    searchPost : function () {

        var searchContent = $('#search-box').val();

        if (searchContent != "") {
            location.href = "/search?searchContent="+searchContent;
        }
        else{
            alert("최소 1글자 이상을 입력해주십시오.")
        }
    },

    commentSave : function (){

        var id = $('#id').val();

        if ($('#comment').val() == ""){
            return false;
        }

        var data = {
            author: $('#commentAuthor').val(),
            content: $('#comment').val()
        };


        $.ajax({
            type: 'POST',
            url: '/api/v1/posts/'+ id +'/comment/',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('댓글이 등록되었습니다.');
            window.location.href = '/posts/inquiry/'+id;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });


    },
    commentDelete : function(post_id, comment_id) {


        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+post_id+'/commentsDelete/'+comment_id,
            dataType: 'Text',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('댓글이 삭제되었습니다.');
            window.location.href = '/posts/inquiry/'+post_id;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    },
     commentUpdate : function (form) {
        var postId = form.querySelector('#postId').value

        const data = {
            id: form.querySelector('#commentId').value,
            content: form.querySelector('#comment-content').value,
            author : form.querySelector('#commentAuthor').value
        }

        if (!data.content || data.content.trim() === "") {
            alert("공백 또는 입력하지 않은 부분이 있습니다.");
            return false;
        }
        const con_check = confirm("수정하시겠습니까?");
        if (con_check === true) {
            $.ajax({
                type: 'PUT',
                url: '/api/v1/posts/' + postId + '/commentsUpdate/' + data.id,
                dataType: 'JSON',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                window.location.reload();
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    }

};

main.init();