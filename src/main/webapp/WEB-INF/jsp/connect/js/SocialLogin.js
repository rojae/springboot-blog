let socials = document.getElementsByClassName("btn_social");
for(let social of socials) {
    social.addEventListener('click', function(){
        let socialType = this.getAttribute('data-social');
        location.href="/oauth2/authorization/" + socialType;
    })
}
