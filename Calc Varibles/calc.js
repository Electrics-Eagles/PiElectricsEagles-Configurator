/*



Made by Alex Zaslavskis 
2021 for Electrics Eagles 

*/


const convert_const=65.5;


function calc_angle_pitch_const(period) {
	var freq=1/period;
	var result=freq/convert_const;
	return result
}


function calc_angle_roll_const(input) {
    var res=input*(Math.PI) / 180;
    return res;
}

function count_display(){
	var input_val=document.getElementById('val').value;

	console.log(input_val);

	var pitch=calc_angle_pitch_const(parseInt(input_val));
	var roll=calc_angle_roll_const(pitch);

	alert("pitch is: "+String(pitch) );
	//alert("1000roll is: "+String(roll*1000) );
	alert("roll is: "+String(roll) );

	var para = document.createElement("br");
	var node = document.createTextNode("This is a new paragraph.");
	para.appendChild(node);

}

