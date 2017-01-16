param gotowka;
param minWolumen;
param maxWolumen;
param kosztStaly;
param amortyzacja;
param stopaPodatkowa;
param reklamaInternet;
param reklamaMagazyny;
param reklamaTelewizja;
param maxPrzelicznikCenyTowaru;
param kosztReklam = reklamaInternet + reklamaMagazyny + reklamaTelewizja;
param minJakosc;

var wolumen >= minWolumen, <= maxWolumen, integer;
var jakosc >= minJakosc, <= 100, integer;
var cena >= 0;
var przychodZeSprzedazy = cena * wolumen;
var kosztZmienny =   0.00012136 * jakosc * jakosc + 0.027331 * jakosc + 0.00000078371 * wolumen + 7.38317;
var kosztProdukcji = kosztStaly + kosztZmienny * wolumen;
var zyskNaSprzedazy = przychodZeSprzedazy - kosztProdukcji - kosztReklam;
var zyskNaDzialalnosci = zyskNaSprzedazy - amortyzacja;
var podatekDochodowy = zyskNaDzialalnosci * stopaPodatkowa;
var zysk = zyskNaDzialalnosci - podatekDochodowy;

subject to o1: gotowka >= kosztProdukcji + kosztReklam + amortyzacja;
subject to o2: cena <= kosztZmienny * maxPrzelicznikCenyTowaru;
subject to o3: zysk >= 0;

maximize f_celu: zysk;


