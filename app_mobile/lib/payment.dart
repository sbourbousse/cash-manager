import 'package:app_mobile/nfcPayment.dart';
import 'package:app_mobile/qrCodePayment.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(Payment());
}

class Payment extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: PaymentPage(),
    );
  }
}

class PaymentPage extends StatefulWidget {
  PaymentPage({Key ?key, this.title = "title"}) : super(key: key);

  final String title;

  @override
  _PaymentPageState createState() => _PaymentPageState();
}

class _PaymentPageState extends State<PaymentPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      // appBar: AppBar(),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            ElevatedButton(
              style: ElevatedButton.styleFrom(
                primary: Color(0xFF474a51), // background
                onPrimary: Colors.white, // foreground
                textStyle: TextStyle(fontSize: 18),
                minimumSize: Size(150, 45),
              ),
              onPressed: () {
                Navigator.push(
                  context,
                  new MaterialPageRoute(builder: (context) => NfcPayment()),
                );
              },
              child: Text("NFC Payment"),
            ),
            ElevatedButton(
              style: ElevatedButton.styleFrom(
                primary: Color(0xFF474a51), // background
                onPrimary: Colors.white, // foreground
                textStyle: TextStyle(fontSize: 18),
                minimumSize: Size(150, 45),
              ),
              onPressed: () {
                Navigator.push(
                  context,
                  new MaterialPageRoute(builder: (context) => QrCodePayment()),
                );
              },
              child: Text("Qr Code Payment"),
            ),
          ],
        ),
      ),
    );
  }
}
