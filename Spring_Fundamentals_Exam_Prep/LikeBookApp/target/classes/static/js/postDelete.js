let del = document.getElementsByClassName('removeBtn');

for (let d = 0; d < del.length; d++) {
    del[d].addEventListener("click",deletePost)
}
function deletePost() {
    var txt;
    if (confirm("Want to delete!")) {
        txt = "Deleted!";
        window.location.href = "http://localhost:8080/home";

    } else {
        txt = "Cancel!";
    }
    console.log(txt);

}