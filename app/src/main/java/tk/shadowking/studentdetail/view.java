package tk.shadowking.studentdetail;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class view extends AppCompatActivity {

    TextView name, contact, gender, year, languages;
    String nameS, contactS, genderS, yearS, payload, lang;
    ImageButton share, mail;
    ImageView music;
    boolean isPlaying = false;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        name = (TextView) findViewById(R.id.displayName);
        contact = (TextView) findViewById(R.id.displayContact);
        gender = (TextView) findViewById(R.id.displayGender);
        year = (TextView) findViewById(R.id.displayYear);
        languages = (TextView) findViewById(R.id.displaylanguages);
        share = (ImageButton) findViewById(R.id.share);
        mail = (ImageButton) findViewById(R.id.mail);
        music = (ImageView) findViewById(R.id.music);
        music.setImageResource(R.drawable.play);
        player = MediaPlayer.create(this, R.raw.song);

        nameS = getIntent().getExtras().getString("Name");
        contactS = getIntent().getExtras().getString("Contact");
        genderS = getIntent().getExtras().getString("Gender");
        yearS = getIntent().getExtras().getString("Year");
        lang = getIntent().getExtras().getString("Languages Known");

        payload = getString(R.string.name)+"\t"+nameS+"\n"
                +getString(R.string.contact)+"\t"+contactS+"\n"
                +getString(R.string.gender)+"\t"+genderS+"\n"
                +getString(R.string.year)+"\t"+yearS+"\n"
                +getString(R.string.langK)+"\t"+lang;

        name.setText(nameS);
        contact.setText(contactS);
        gender.setText(genderS);
        year.setText(yearS);
        languages.setText(lang);

        Toast.makeText(this, "Hello " + getIntent().getExtras().getString("Name"), Toast.LENGTH_SHORT).show();

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, payload);
                try {
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendMail = new Intent(Intent.ACTION_SEND);
                sendMail.setType("text/html")
                        .putExtra(Intent.EXTRA_EMAIL, "")
                        .putExtra(Intent.EXTRA_SUBJECT, "Student Detail of "+nameS)
                        .putExtra(Intent.EXTRA_TEXT, payload);
                /*if (sendMail.resolveActivity(getPackageManager()) != null) {
                    startActivity(sendMail);
                }
                */
                try{
                    startActivity(Intent.createChooser(sendMail,"Share Detail:"));
                }catch(android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "No Sharing App Found", Toast.LENGTH_SHORT).show();
                }

            }
        });


        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isPlaying){
                    player.start();
                    isPlaying = true;
                    music.setImageResource(R.drawable.pause);
                }
                else{
                    player.pause();
                    isPlaying = false;
                    music.setImageResource(R.drawable.play);
                }
            }
        });
    }
}
