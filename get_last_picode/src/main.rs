use serde_json::{Result, Value};
use std::fs::File;
use std::io;
use std::io::prelude::*;
use std::path::Path;
use url::Url;
use sha256::digest_file;
use std::fs;


struct release_data {
    link: String,
    comment: String,
    rating: String,
    date: String,
    checksum:String
}

fn fetch_last_release() -> release_data {
    let resp =
        reqwest::blocking::get("https://electrics-eagles.github.io/PiElectricsEagles/release.json")
            .unwrap()
            .text()
            .unwrap();

    let v: Value = serde_json::from_str(&resp).unwrap();

    let return_release_data = release_data {
        link: v[0]["link"].to_string(),
        comment: v[0]["comment"].to_string(),
        rating: v[0]["rating"].to_string(),
        date: v[0]["date"].to_string(),
        checksum: v[0]["checksum"].to_string(),
    };

    return_release_data
}

fn fetch_file(data: release_data) {
    println!("Rating is : {}", data.rating);
    println!("Comment is : {}", data.comment);
    println!("Release date is : {}", data.date);

    println!("{}", "Fetching file it can take while.....");
    download_file(data.link);


    check_sum_check(data.checksum);
}

fn download_file(mut target: String) {
    
    //&target[1..target.len()-1] // remove first char anbd last that are " "
//&target[1..target.len() - 1
    let response = reqwest::blocking::get(target).unwrap();

    let path = Path::new("./download.zip");

    let mut file = match File::create(&path) {
        Err(why) => panic!("couldn't create {}", why),
        Ok(file) => file,
    };
    let content = response.text().unwrap();
    file.write_all(content.as_bytes()).unwrap();
}

fn check_sum_check(mut checksum: String) {
let input = Path::new("./download.zip");
    let val = digest_file(input).unwrap();

    if val ==  &checksum[1..checksum.len() - 1] {
    	 println!("Okay checksum is matching");
    }
    else {
    	println!("Fatal Checksum is NOT NOT matching. Restart tool again")
    }
}

fn main() {
    println!("Small tool that will fetch last verison of PiElectricsEagles Code binary");
    println!("Fetching .json with all releases now.");
    println!("{}", "Running as user interact mode");
    while true {
        println!(
            "{}",
            "Do you want install to /usr/bin downloaded code ? : (y)/(n)"
        );

        let mut input = String::new();

        io::stdin()
            .read_line(&mut input)
            .ok()
            .expect("Couldn't read line");

        //println!("Your input: {}", input);
        if input == "y\r\n" {
            break;
        }

        if input == "n\r\n" {
            fetch_file(fetch_last_release());

            break;
        } else {
            println!("{}", "Fatal error. Try again");
        };
    }

    //println!("{}",release_data.link );
}
