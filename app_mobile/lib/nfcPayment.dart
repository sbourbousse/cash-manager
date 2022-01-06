import 'package:flutter/material.dart';

void main() {
  runApp(NfcPayment());
}

class NfcPayment extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: NfcPaymentPage(),
    );
  }
}

class NfcPaymentPage extends StatefulWidget {
  NfcPaymentPage({Key ?key, this.title = "title"}) : super(key: key);

  final String title;

  @override
  _NfcPaymentPageState createState() => _NfcPaymentPageState();
}

class _NfcPaymentPageState extends State<NfcPaymentPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      // appBar: AppBar(),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'NFC Payment Page',
            ),
          ],
        ),
      ),
    );
  }
}
