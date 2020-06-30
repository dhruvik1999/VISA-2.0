package com.visa.ATM.QrCodeHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.visa.ATM.R;

public class QrCodeGenerater extends AppCompatActivity {
    ImageView imageView;
    Button button;
    EditText editText;
    String EditTextValue ;
    Thread thread ;
    public final static int QRcodeWidth = 500 ;
    Bitmap bitmap ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_generater);
        imageView = (ImageView)findViewById(R.id.imageView);
        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                EditTextValue = editText.getText().toString();
                EditTextValue = "{\n" +
                        "\"acquirerCountryCode\": \"457\",\n" +
                        "\"acquiringBin\": \"408999\",\n" +
                        "\"amount\": \"124.02\",\n" +
                        "\"businessApplicationId\": \"AA\",\n" +
                        "\"cardAcceptor\": {\n" +
                        "\"address\": {\n" +
                        "\"country\": \"USA\",\n" +
                        "\"county\": \"081\",\n" +
                        "\"state\": \"CA\",\n" +
                        "\"zipCode\": \"94404\"\n" +
                        "},\n" +
                        "\"idCode\": \"ABCD1234ABCD123\",\n" +
                        "\"name\": \"Visa Inc. USA-Foster City\",\n" +
                        "\"terminalId\": \"ABCD1234\"\n" +
                        "},\n" +
                        "\"cavv\": \"0700100038238906000013405823891061668252\",\n" +
                        "\"foreignExchangeFeeTransaction\": \"11.99\",\n" +
                        "\"localTransactionDateTime\": \"2020-06-28T20:45:02\",\n" +
                        "\"retrievalReferenceNumber\": \"330000550000\",\n" +
                        "\"senderCardExpiryDate\": \"2015-10\",\n" +
                        "\"senderCurrencyCode\": \"USD\",\n" +
                        "\"senderPrimaryAccountNumber\": \"4895142232120006\",\n" +
                        "\"surcharge\": \"11.99\",\n" +
                        "\"systemsTraceAuditNumber\": \"451001\",\n" +
                        "\"nationalReimbursementFee\": \"11.22\",\n" +
                        "\"cpsAuthorizationCharacteristicsIndicator\": \"Y\",\n" +
                        "\"addressVerificationData\": {\n" +
                        "\"street\": \"XYZ St\",\n" +
                        "\"postalCode\": \"12345\"\n" +
                        "},\n" +
                        "\"settlementServiceIndicator\": \"9\",\n" +
                        "\"colombiaNationalServiceData\": {\n" +
                        "\"countryCodeNationalService\": \"170\",\n" +
                        "\"nationalReimbursementFee\": \"20.00\",\n" +
                        "\"nationalNetMiscAmountType\": \"A\",\n" +
                        "\"nationalNetReimbursementFeeBaseAmount\": \"20.00\",\n" +
                        "\"nationalNetMiscAmount\": \"10.00\",\n" +
                        "\"addValueTaxReturn\": \"10.00\",\n" +
                        "\"taxAmountConsumption\": \"10.00\",\n" +
                        "\"addValueTaxAmount\": \"10.00\",\n" +
                        "\"costTransactionIndicator\": \"0\",\n" +
                        "\"emvTransactionIndicator\": \"1\",\n" +
                        "\"nationalChargebackReason\": \"11\"\n" +
                        "},\n" +
                        "\"riskAssessmentData\": {\n" +
                        "\"delegatedAuthenticationIndicator\": true,\n" +
                        "\"lowValueExemptionIndicator\": true,\n" +
                        "\"traExemptionIndicator\": true,\n" +
                        "\"trustedMerchantExemptionIndicator\": true,\n" +
                        "\"scpExemptionIndicator\": true\n" +
                        "},\n" +
                        "\"visaMerchantIdentifier\": \"73625198\"\n" +
                        "}";
                try {
                    bitmap = TextToImageEncode(EditTextValue);

                    imageView.setImageBitmap(bitmap);

                } catch (WriterException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.QRCodeBlackColor):getResources().getColor(R.color.QRCodeWhiteColor);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }
}