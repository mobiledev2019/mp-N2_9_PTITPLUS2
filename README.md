# mp-N2_9_PTITPLUS2
mp-n2_9_ptitplus2 created by GitHub Classroom
commit first theme + homeapp
https://fs8.uplod.net/fibszqubeisjxish/udemy-go-full-stack-with-spring-boot-and-react.zip
https://www.journaldev.com/11655/spring-rabbitmq


var req = new XMLHttpRequest();
req.open("POST", "https://httpbin.org/post", false);
req.setRequestHeader("Content-Type", "application/json; charset=UTF-8");

var jsonBody = {
  "name" : "Lam Pham",
  "url" : "completejavascript.com"
};
req.send(jsonBody);

console.log(req.status);
console.log(req.responseText);
const obj = JSON.parse(req.responseText);
console.log(obj.origin)



{"code":200,"desc":"Successful","data":{"token":"CMzO7RT8J4mBv2gEtYDe9J+ZdOqcKdPlRAnt75WttQVvvOEWI5MQKzmfhz3l/NHqa+CugrdhgUGz0+ztQWA3HO39FaTB9zHCsunV1w7N9Af1y8jwarffIKsZWGiUHiKM8Ns+xMezRQjk40CZv6Neaw\u003d\u003d","username":"84375650802"},"dataEnc":"Gxe2F4FqUdnou++uuRheGkDVdtdmDGwfe0+/qQmUFTcSy+VnBl8l9jIv1TXdfN+eQsApJxoSwmUTDao3tw+TY74bVwwqQ445MaYVUbrJ3PmF9u1iSnNADEYAgRZvkvoS9WcnYd5be7B+UCCkTUyeIdpZ+q0sFPGe4H14s4KHOSLWy8MHSU5DqT45NEJX5ah2nRsit2r/7SmFNItFsnRwJfK6WzhGDxKMJTt49sNHQncpLrMKOciOoLPraZoRGW0axDzuCcICxWAcJ/79"}

{
    "code": 200,
    "desc": "Successful",
    "data": {
        "token": "9HoDrd9PPnoyQfKxvlppS3rh0lNA17myM3QC2OBYeOYmxHiAIwmLZtWHfmvDlBDNnnzZCBmiz5cXjAYATlHypgCqtU0hRYELQmUNoe2LRwz2C/77uswW4X2a+3QHGRSSRz0Ygj+0ss/kN8+pcW7Lww==",
        "channelInfo": {
            "id": 69835,
            "name": " Hương hihi",
            "url_avatar": "http://cdn2.keeng.net/playnow/images/chanels/20190827/Xit7KX7b2EmRIyhN.jpg",
            "is_follow": 0,
            "url_images_cover": "http://cdn2.keeng.net/playnow/images/chanels/banner/20171211/7.jpg",
            "url_images": "http://cdn2.keeng.net/playnow/images/chanels/20190827/Xit7KX7b2EmRIyhN.jpg",
            "numfollow": 0,
            "is_registered": 0,
            "type": 1,
            "isMyChannel": 1,
            "description": "huong hehe",
            "isOfficial": 0,
            "createdDate": "2019-08-10 17:51:40.0",
            "numVideo": 2,
            "hasFilmGroup": 0,
            "categoryid": 0,
            "link": "http://video.mocha.com.vn/-Huong-hihi-cn69835.html",
            "status": 2
        },
        "username": "84366914487"
    },
    "dataEnc": "TKV/1MjyNw/Mh6c+zXtG0qTlaqK/HBkiGCaKoOopLokMo2KsN5w8LtxbUFvyhAbNHGkLVw6Jgc+rIKFFC+kDOyPcIoGxGCcfzmRN1DBU8twXh5WGnTKflIQzq0LSWjFGrwonPgNtFjJlMpMdOQnokX7Ddk42fNw4vewt6J0ykQopAd7sJsL6+ji6uSElMC8wmJNmq6ZSqnsY80td7ProdBWoXwYHMQojMljLZvaDmRIl2tW6TeqefzqQMHucIZtVlL+CxkY/GRbNv+Dm5oNpYcMHdYsABreiyN2lbmEK7IEWjDEwbmHcmc6rSH5KmMyrnxH5TTAs4E7yRUvUv5eW3dfG1GXi/DsfEmpb/d9Pxyd9AxZY0rDBq5iff9lr9J4/BywS0R303ugPBef8o0bv7+lEzkdUfg87n/2ZscJrEHO14IFBMwAD32/AGxQ2tB58JQP+AvtWElJUiomwpleJ9EcjeUDdLuyalM05udiGBf6qmM73VRAaM6m6DQ+j5/o6F5jPDgrPUuInDJ1RDDKhCt9mpdBl5YFwGJ8LOidxkffPylAzVPRcxszeXPiiPwMvdI6CuErHO/oAfFly7AwabLuu/0F1+qeZAPIzrSu8bBMRQdLAQ1vBbEc3IGGAsn3F8ZNM17sUoLsDv34xbrMBdGTiEnBsenU6ih+7h1xfvTWDJ+cvXQniqujp6Mr6t82dukuO+JoWyeNKpiFXDmDPY+7D4h6VJRV3MBxFUIVj3mE2OnfM9pE7UFel6N5f2CBnpMGITbUXsPYaCX7x7BAZG+uGrr9mbBGqTF+aYJhwBMdH6rEcORgIUJBzUEeoeEtdGM+DOvWTwgZtYDdjENeNbTSy+Fpp1JIBjOUmmA70WMtUQVbEH4EGf26Nr+Qp4Y1SOlLwYHZbeir0jqt82emRLwXentEkP6Z2l0ueXTK0EFQw44YqFpO224zxzM2YER3ebqAa5eEAN7Np/CVgvSOeu5SDDDaW9f4vvbUhkite38Kt1cCDmJEpk+uRPUZpZX1aksmDfjH0Q2JfjzoQ8JUrA9sMBzE="
}






https://secforlearn.blogspot.com/2016/08/implement-suricata-idsips-with-elk-why.html
