/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Riccardo Gonzini
 */
public class limiteBordi {
    
    // il limiteBordi è un array contenente due "controlli" che virtualmente è come se fossero 2 muri invisibili.
    // il primo "muro" è per gli spostamenti verticali, ed ha un valore da 0 a dimensione - 1, appena combacia con uno dei due
    // estremi esso annulla gli spostamenti che vanno oltre l'estremo che si vuol superare in modo da "bloccarlo",
    // ad esempio, se il nostro pg arriva alla prima linea (cioè "0", con il relativo muro che ha valore "0") i
    // movimenti verso l'alto verranno bloccati. Stessa cosa per tutti gli altri versi.
}
