package dam.pmdm.pokemonappnz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dam.pmdm.pokemonappnz.databinding.FragmentPokemonDetailBinding;


public class PokemonDetailFragment extends Fragment {

private FragmentPokemonDetailBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding=FragmentPokemonDetailBinding.inflate(inflater,container,false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtener datos del argumento que inicia este fragmento

        if(getArguments()!=null){
            String pokemonName=getArguments().getString("pokemonName");
            String pokemonIndex=getArguments().getString("pokemonIndex");
            String pokemonTypes=getArguments().getString("pokemonTypes");
            String pokemonWeight=getArguments().getString("pokemonWeight");
            String pokemonHeight=getArguments().getString("pokemonHeight");

            // Asignar los datos a los componentes
            binding.pokemonName.setText(pokemonName);
            binding.pokemonIndex.setText(pokemonIndex);
            binding.pokemonTypes.setText(pokemonTypes);
            binding.pokemonWeight.setText(pokemonWeight);
            binding.pokemonHeight.setText(pokemonHeight);
        }
        //  NavController para la navegación
        NavController navController = NavHostFragment.findNavController(PokemonDetailFragment.this);
        binding.btnBack.setOnClickListener(v -> {
            // Navegar al fragmento de Pokémon Capturados
            navController.navigate(R.id.capturedFragment);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.detail_title);
        }
    }
}