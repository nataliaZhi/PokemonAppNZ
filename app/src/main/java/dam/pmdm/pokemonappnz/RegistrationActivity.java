package dam.pmdm.pokemonappnz;

import androidx.appcompat.app.AppCompatActivity;


public class RegistrationActivity extends AppCompatActivity {

//    private FirebaseAuth mAuth;
//    private FirebaseFirestore db;
//    private ActivityRegistrationBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_registration);
//
//        mAuth = FirebaseAuth.getInstance();
//        db = FirebaseFirestore.getInstance();
//
//        binding.registerButton.setOnClickListener(v -> {
//            String firstName = binding.editFirstName.getText().toString();
//            String lastName = binding.editLastName.getText().toString();
//            String email = binding.editEmail.getText().toString();
//
//            if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(email)) {
//                Toast.makeText(RegistrationActivity.this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                Toast.makeText(RegistrationActivity.this, "Correo electrónico no válido", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            // Llamamos al método para registrar el usuario
//            registerUser(firstName, lastName, email);
//        });
//    }
//
//    public void registerUser(String firstName, String lastName, String email) {
//        // Crear el mapa de usuario para Firestore
//        Map<String, Object> user = new HashMap<>();
//        user.put("first", firstName);
//        user.put("last", lastName);
//        user.put("email", email);
//        user.put("createdAt", FieldValue.serverTimestamp()); // Timestamp de creación
//
//        // Agregar documento al Firestore
//        db.collection("users")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d("RegisterActivity", "User added with ID: " + documentReference.getId());
//                        // redirigir a la pantalla de inicio
//                        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("RegisterActivity", "Error adding document", e);
//                    }
//                });
//    }
}
