reset;
param gotowka >= 0;
param kredyt >= 0;
param maxWolumen;
param maxJakosc;
param minJakosc;
param kosztStaly;
param amortyzacja;
param maxKredyt;
param stopaPodatkowa;


var wolumen >=0, <= maxWolumen, integer;
var jakosc >= minJakosc, <= maxJakosc, integer;
var cena >= 7, <= 27;
var reklamaInternet >= 0;
var reklamaMagazyny >= 0;
var reklamaTelewizja >= 0;
var nowyKredyt >= 0, <= maxKredyt;
var splacanaRata >= 0;
var przychodZeSprzedazy = cena * wolumen, >= 0;
var kosztyProdukcji >= 0;
var kosztZmienny >= 0;
var kosztReklam >= 0;
var zyskNaSprzedazy >= 0;
var zyskNaDzialanosciOperacyjnej >= 0;
var zyskNaDzialalnosciGospodarczej >= 0;
var podatekDochodowy >= 0;
var zysk >= 0;

maximize s: przychodZeSprzedazy;