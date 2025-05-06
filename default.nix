{ pkgs ? import <nixpkgs> {} }:

pkgs.stdenv.mkDerivation {
  name = "digital-twin";
  src = ./.;

  buildInputs = [
    pkgs.maven
    pkgs.openjdk17
  ];

  buildPhase = ''
    mvn clean package
  '';

  installPhase = ''
    mkdir -p $out
    cp -r target $out/
  '';
}
