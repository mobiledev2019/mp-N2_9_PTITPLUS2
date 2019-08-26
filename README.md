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

