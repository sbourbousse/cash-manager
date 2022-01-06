import 'package:app_mobile/main.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'dart:developer';
import 'dart:async';
import 'package:flutter/material.dart';
import 'global.dart' as global;

// fonction fetch basique
Future<List<dynamic>> fetch(String url) async {
  //log("tu passe par la");
  var result = await http.get(Uri.parse(url));
  if (result.statusCode == 200) {
    //log("tu passe dans la boucle");
    //print(json.decode(result.body));
    return json.decode(result.body);
  } else {
    print("ErrorCode : ${result.statusCode}");
    throw Exception('Failed to load post');
  }
}

Future addProduct(Map<dynamic, dynamic> snapshot, int qte) async {
  var reponseData;
  var createCart = {};
  var addProductCart = {};
  int id = snapshot["id"];
  id = int.parse("${snapshot["id"]}");

  createCart = {
    "cartProducts": [
      {
        "productRepresentation": {"id": id},
        "amount": qte
      }
    ]
  };
  String jsonAddCart = json.encode(createCart);

  addProductCart = {
    "productRepresentation": {"id": id},
    "amount": qte
  };
  String jsonAddProductCart = json.encode(addProductCart);

  // Post product according to the cartId
  if (cartId == null || cartId == 0) {
    // create a new cart and post product above
    final response = await http.post(
      Uri.parse(global.url + '/cart'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonAddCart,
    );

    if (response.statusCode == 200) {
      reponseData = json.decode(response.body);
      cartId = int.parse("${reponseData["id"]}");
      print(response.statusCode);
      print("Element Ajoute");
    } else {
      print("ErrorCode : ${response.statusCode}");
      return null;
    }
  } else {
    // poste product according to the cartId
    final response = await http.put(
      Uri.parse(global.url + '/cart/add/${cartId}'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonAddProductCart,
    );

    if (response.statusCode == 200) {
      print(response.statusCode);
      print("Element Ajoute");
    } else {
      print("ErrorCode : ${response.statusCode}");
      return null;
    }
  }
}

totalPrice(Map<dynamic, dynamic> snapshot) {
  double totalPrice = 0;
  totalPrice = snapshot["productRepresentation"]["price"] * snapshot["amount"];
  return totalPrice;
}

totalPriceCart(AsyncSnapshot snapshot) {
  double totalPriceCart = 0;
  for (var i = 0; i < snapshot.data.length; i++) {
    totalPriceCart += totalPrice(snapshot.data[i]);
  }
  return totalPriceCart;
}
