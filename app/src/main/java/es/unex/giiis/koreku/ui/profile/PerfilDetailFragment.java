package es.unex.giiis.koreku.ui.profile;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import es.unex.giiis.koreku.Perfil;
import es.unex.giiis.koreku.R;
import es.unex.giiis.koreku.roomdb.DateConverter;


public class PerfilDetailFragment extends Fragment {

    private Perfil mCon;

    public PerfilDetailFragment() {
        // Required empty public constructor
    }

    public static PerfilDetailFragment newInstance(Perfil c) {
        PerfilDetailFragment fragment = new PerfilDetailFragment();
        Bundle args = new Bundle();
        args.putLong("id", c.getId());
        args.putString("title",c.getTitle());
        args.putString("phone",c.getPhone());
        args.putString("correo",c.getMail());
        args.putLong("dateLong",DateConverter.toTimestamp(c.getDate()));
        args.putString("image",c.getImage());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = this.getArguments();
        if (args != null) {
            mCon = new Perfil(args.getLong("id"), args.getString("title"), DateConverter.toDate(args.getLong("dateLong")), args.getString("phone"), args.getString("correo"), args.getString("image"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.profile_detail, container, false);
        // Show item content
        TextView mTitle = v.findViewById(R.id.nombredetaiprofile);
        TextView mTelefono = v.findViewById(R.id.telefonodetailprofile);
        TextView mCorreo = v.findViewById(R.id.correodetailprofile);
        ImageView image = v.findViewById(R.id.imagenprofileitem);
        mTitle.setText(mCon.getTitle());
        mCorreo.setText(mCon.getMail());
        mTelefono.setText(mCon.getTitle());
        image.setImageURI(Uri.parse(mCon.getImage()));
        return v;
    }

    @Override public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(mCon.getTitle());
    }

    @Override public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar()
                .setTitle(((AppCompatActivity)getActivity()).getTitle());
    }
}